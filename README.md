# Happiest Minds - TechGig Hiring Challenge

The project is created from `Spring Boot v2.1.6.RELEASE`. As part of the given exercise, the following libraries were included.

1. Resource End-point: `POST:/persist-words` - It is helpful to POST any .txt file with the word & meaning combination separated by `:` and each combination should be separated by `;`. This way the request can be served successfully.
     - All Words from a .txt file will be retrieved and recorded to dictionary. 
     - Once a word with meaning exists in dictionary (i.e. in DB), then that same can not be persisted in DB. It make sures the duplicates are not allowed.
     - If the file is not of type `.txt`, system with throuw 400 error response.
     - When multiple .txt files being uploaded sequentially, the system ensures to remove the duplicaties in all file. After all files got processed, a union of all words in all files can be seen in dictionary.

**Sample .txt file content**: `accurate:free from mistakes or errors; accept:to receive or take (something offered);`.
    
2. Resource End-point: `GET:/search-word` - It helps to search a word in the word dictionary.
     - If the given word is found, the associated meaning will be retrieved
     - If word is not found, the system will throw 404 error response.

Along with above implementation, the following libraries have been incorporated. 

1. Swagger UI
2. Mapstruct
3. Mockito
4. Exception Handling
