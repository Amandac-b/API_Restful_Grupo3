# API Endpoints

## User Controller
- **GET** `/users/{id}`: Retorna os detalhes de um usuário específico.
- **POST** `/users`: Cria um novo usuário.
- **DELETE** `/users/{id}`: Remove um usuário específico.
- **PUT** `/users`: Atualiza os detalhes de um usuário.
- **GET** `/users/{id}/relationship/following`: Lista os usuários que um usuário específico está seguindo.
- **GET** `/users/{id}/relationship/followers`: Lista os seguidores de um usuário específico.

## Post Controller
- **GET** `/posts/{id}`: Retorna os detalhes de uma postagem específica.
- **POST** `/posts`: Cria uma nova postagem.
- **PUT** `/posts/{id}`: Atualiza uma postagem específica.
- **DELETE** `/posts/{id}`: Remove uma postagem específica.

## Comment Controller
- **GET** `/comments/{id}`: Retorna os detalhes de um comentário específico.
- **POST** `/comments`: Cria um novo comentário.
- **PUT** `/comments/{id}`: Atualiza um comentário específico.
- **DELETE** `/comments/{id}`: Remove um comentário específico.
