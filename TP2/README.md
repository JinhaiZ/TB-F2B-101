# 101 TP2

## Partie 1 : un service avec UDP

- Consultez le code et représenter les différentes classes avec UML ou un autre formalisme.

- Exécutez le code après l’avoir compilé. Indiquez dans la ligne de commande le numéro du port utilisé par le serveur lors de son lancement. Que se passe t il lorsque vous lancez le client d’abord puis le serveur ? Le serveur puis le client ? pourquoi ?

- Pourquoi avons nous introduit la notion de Helper pour le client ? expliquez en particulier les avantages d’une telle approche en termes de séparation des parties applications et service.

    hide details to the Daytime client, encapsulate the detail manipuation, easy to use Daytime client

- Pourquoi est il nécessaire pour le serveur d’utiliser la méthode
receiveMessageAndSender plutôt que la méthode receiveMessage ?

    This method can get the sender's address and port, then server can use these infomation to contact the client

## Partie 2 : le même service avec TCP

On s’intéresse cette fois encore au même service que précédemment.

- expliquez les différences avec le code précédent. En particulier expliquez les différences côté serveur pour déterminer qui l’appelle.

    UDP datagramSocket, no connection, server needs to find address and port of the client in order to send response
    TCP StreamSocket, establish connection between server and client

- Introduisez artificiellemen,t un délai de service du côté du serveur (avec la commande Thread.sleep (3000) – le thread courant est bloqué pendant 3 secondes). Activez le serveur puis 2 clients. Que se passe t il ? expliquez !

    Lance 2 clients, firstly lance client1 then client2, for example, the server firstly established a connection with client1, client1 wait 3s then get message, then server established a connection with client2, then client2 waits for 3s and get the message


## Partie 3 : vers un service complet – une calculette à distance

- compilez dans un premier temps les programmes avec la commande « javac Echo*2.java » 

- lancez le serveur puis le client. Le client attend de votre part une série de chaînes de caractères et la dernière est terminée par « . ».

- pourquoi pouvons nous considérer que ce serveur est itératif et en mode connecté ? expliquez le rôle du « helper » que l’on définit du côté du client.

    server never close the DataSocket except when an end message is recevied. Client Helper defines 2 functions, sendMessage (send a message to server and get its response)and done (send a end message to sever and close its connection TCP to server)

- considérez maintenant que la chaîne envoyée vers le serveur est une commande du type « ADD 2 3 » (qui demande la somme de deux nombres). Modifiez le serveur pour qu’il puisse extraire les paramètres et calculer la somme avant de renvoyer le résultat au client. Pour cela vous pourrez vous aider des méthodes Java « split » et « length » pour trouver les différentes parties d’une chaîne séparées par au moins un blanc. Vous compléterez le code en essayant de structurer votre code. Il s’agit de séparer la partie traitement de requêtes de la partie action. Vous pouvez considérer que le serveur fait le traitement de requêtes et que les actions (somme, ...) sont des procédures que le serveur appelle.

- Compléter le code de façon à ce qu’il puisse désormais réaliser les multiplications – une commande du type MUL 2 3

- Modifiez le programme côté client pour ne plus lire une chaîne de commande depuis le clavier mais qu’il appelle directement une méthode ADD (2,4) pour demander au serveur l’addition de ces deux nombres (respectivement MUL (2, 3) pour invoquer la multiplication). Utilisez pour cela une approche similaire à celle proposée par la méthode getEcho.

- Que faut il faire si on souhaite enfin considérer une exception déclenchée par le serveur et propagée vers le client ?
    
    when exception happens in server, server sends an error message or an error code to client

- Si vous considérer maintenant d’autres fonctions pour la calculette (Div, sub, pow etc...), pourquoi et comment pourrait on automatiser la génération du code côté client et serveur ?

    Why: generation of code stub can facilitate the development process and offers a reliable solution to deliver code that conforms to interface.
    How: use interface description language like IDL, with command line, code stub can be generated, for example, function signature. The implementation method is free for developers to choose.