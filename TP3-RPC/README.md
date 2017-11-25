# F2B 101 TP3

## Partie 1: client serveur et calculatrice

Q3 : quelle est la nature du code généré pour les 2 derniers fichiers ?
    
    the calc_clnt_main.c is a sample client code that uses remote procedures while the calc_svc_proc.c is a sample server code that defines remote procedures

    `sudo apt install rpc bind` to enable RPC service

Q4 : quelles sont les séquences de traitement et d’appels qu’un serveur réalise lorsqu’un message RPC CALL arrive ? Donnez des explications et illustrez le.

    in the file calc_svc.c, see the main() function. The server firstly try to establish a UDP socket, then try to establish a TCP connction with the client. Then function svc_run is called to listen requests from client, for example, client requests for ADD, then fucntion ADD in the server side will be called and the server returns the results to the client through svc_sendreply().

Q5 : regarder la documentation en ligne, et précisez les arguments de la commande svc_send reply. A quoi sert chacun des arguments ?

> svc_sendreply -- called by RPC service's dispatch routine

> bool_t svc_sendreply(SVCXPRT *xprt, xdrproc_t outproc, caddr_t out);

> Description

> Called by an RPC service's dispatch routine to send the results of a remote procedure call. The parameter xprt is the request's associated transport handle; outproc is the XDR routine which is used to encode the results; and out is the address of the results. This routine returns one if it succeeds, zero other-wise.


Q6 : comment les paramètres des services (ADD par exemple) sont-ils codés ?

```c
    case ADD:
		_xdr_argument = (xdrproc_t) xdr_int; // function used to decode
		_xdr_result = (xdrproc_t) xdr_int; // fucntion used to encode
		local = (char *(*)(char *, struct svc_req *)) add_1_svc; // the function
		break;
        
    ...

	if (!svc_getargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		svcerr_decode (transp);
		return;
	} // decode arguments

    result = (*local)((char *)&argument, rqstp); // call the fucntion

	if (result != NULL && !svc_sendreply(transp, (xdrproc_t) _xdr_result, result)) {
		svcerr_systemerr (transp);
	} // encode arguments
```

## Transformation vers une calculatrice de nombres complexes.

On s’intéresse désormais à la même calculatrice qui gère cette fois ci non plus des entiers mais des complexes. On fournit pour cela une nouvelle interface calccomplexe.x

Reprenez les différentes étapes de génération telles que présentées précédemment.

Q7 : quelles sont les différences dans la spécification ? Comment ce nouveau type de données est-il gérer ? où et comment ?

    input type changed, from int to complexe. This new type is a structure defined in calcomplexe.x and calccomplex_xdr.c is generated to implement this new type
    
```c
    struct complexe {
        int pentiere ;
        float pimaginaire;
    };
```

Q8 : précisez enfin comment les erreurs sont gérées par ce type de RPC. Regardez le code et précisez les différentes erreurs qui sont mentionnées et référencées. Comment une applictaion
peut elle les traiter ?

- connection error, UDP or TCP, managed by _svc.c (server skeleton)
- timeout error, manage by _clnt.c (client stub)
- clnt_pcreateerror, clnt_perror managed by _clnt_main.c
- svcerr_noproc, svcerr_decode, svcerr_systemerr managed by  _svc.c (server skeleton)
- when client calls a service that doesn't exist, `(void) svc_sendreply (transp, (xdrproc_t) xdr_void, (char *)NULL);` return a NULL result

## Partie 2 : Application du dictionnaire

### 2.1 - Application non répartie

quelles sont les structures de données utilisées ? quelles sont les structures de contrôle utilisées ?

### 2.2 - Application répartie