# GUÍA CON PEQUEÑOS CONSEJOS Y RECOMENDACIONES

Pequeña serie de consejos y recomendaciones para enfrentarse a la implementación de un algoritmo para un ejercicio:

## Recorrido de grafos:
Aquí lo básico de lo básico, tener clara la diferencia entre un recorrido en Anchura y uno en Profundidad (igual ayuda pensar que uno recorre "horizontalmente" y otro "verticalmente", como sus nombres indican).

No te olvides de tu lista de **visitados**.

Para el recorrido en Anchura necesitarás una **cola** para que no haya confusiones a la hora de visitar el siguiente nodo.

Para el recorrido en Profundidad recuerda incluir en el método recursivo el **padre** del nodo que vas a visitar. Si se trata de encontrar ciclos en un grafo este algoritmo es la clave.

## Algoritmos voraces:
La clave de los algoritmos voraces es sin lugar a duda el almacenamiento de los datos y que estos estén correctamente ordenados (en función de lo que te pidan en el enunciado). Normalmente te dirán que saques un resultado en función de una de sus características, pero lo más común es que haya otro dato que suponga una penalización a la hora de elegir (por ejemplo, en el ejercicio de Dark Souls, la defensa y el peso de los objetos), por lo tanto lo mejor es crearte un atributo ratio que se genere en función de los dos anteriormente mencionados, y luego elegir basándote en este.

Lo más cómodo en estos ejercicios es hacerte una clase del elemento con el que estés trabajando (con sus atributos y demás) y que esta implemente *Comparable*, lo cual te generará el método *compareTo* con el que podrás ordenar tu lista de elementos en función de lo que quieras.

*(this.atributo, o.atributo)* -> ordena de menor a mayor en función de *atributo*

*(o.atributo, this.atributo)* -> ordena de mayor a menor en función de *atributo*

Luego para lo que es el algoritmo como tal no sigo ningún truco en concreto, simplemente leer bien las indicaciones que te dan en el enunciado e intentar llevarlas a cabo con el código. A fin de cuentas lo que van a hacer estos algoritmos es coger lo primero que tengan en la lista de elementos que les pases, de ahí que sea tan importante tenerla bien ordenada.

## Divide y Vencerás:
Con estos algoritmos, al tener que "partir" por la mitad una lista siempre necesitarás crearte una variable para situarte en el centro de esta, y luego ya en función de si necesitas o no quedarte con las dos mitades pues las almacenas o no. Por ejemplo, en una búsqueda binaria cuando compruebas el centro o justo aciertas en el elemento o deduces que lo que buscas está en el lado izquierdo o en el derecho, por lo tanto puedes descartar el que no contenga tu elemento. Por otro lado, en un mergesort, necesitas quedarte con las dos mitades siempre para aplicarles el algoritmo, por lo tanto ahí si que necesitarás almacenarlas.

## Backtracking

Lo primero de todo a tener en cuenta con el backtracking es lo más evidente. Lo normal es que se solucionen con recursividad, y si esta ya puede ser complicada de entender en sus formas más simples, a medida que escribas código todo va a ser más difuso. Por lo tanto cuanto más limpio y claro seas escribiendo tu programa menos te liarás.

Es posible que te interese crearte una variable global que puedas modificar en cualquier momento, lo cual es muy útil para ejercicios como el de encontrar la mejor ruta en un laberinto.

Ten siempre muy claras y presentes las condiciones que finalizarán tu algoritmo, pues son la clave para que la recursividad funcione y para evitar problemas como la recursividad infinita. Organízate una buena serie de subprogramas que sirvan para comprobar estas condiciones.
