!/bin/bash

#Añade todos los archivos
	
	git add .

# Esto nos pedira el mensaje del commit
	
	echo "Introduce el mensaje del commit:"
	read TXT
	
	git commit -m "$TXT"
# hacemos el push para subirlo a mi repositorio personas
	echo "Introduce como se llama la carpeta del repositorio:"
	read TXT
	echo "Introduce el nombre de la rama en que la deseas subir:"
	echo RAMA
 git push https://github.com/paferdi94/$TXT.git $RAMA