# F2B 101 TP3

Q3 : quelle est la nature du code généré pour les 2 derniers fichiers ?
    
    the calc_clnt_main.c is a sample client code that uses remote procedures while the calc_svc_proc.c is a sample server code that defines remote procedures

    `sudo apt install rpc bind` to enable RPC service

Q4 : quelles sont les séquences de traitement et d’appels qu’un serveur réalise lorsqu’un message RPC CALL arrive ? Donnez des explications et illustrez le.

    in the file calc_svc.c, see the main() function. The server firstly try to establish a UDP socket, then try to establish a TCP connction with the client. Then function svc_run is called to listen requests from client, for example, client requests for ADD, then fucntion ADD in the server side will be called and the server returns the results to the client through svc_sendreply().



