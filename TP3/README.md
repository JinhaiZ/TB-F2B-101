# F2B 101 TP3

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