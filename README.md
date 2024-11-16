- API 명세서

| 기능        | URL                | METHOD | Request Header | Request                                                                     | Response                                                                                                                                                      | Response Header |
  |-----------|--------------------|--------|----------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|
| 회원 가입     | /api/auth/sign-up  | POST   |                | ```{ "username": "Alice", "password": 30 }```                               | ```{ "result": "SUCCESS", "data": true }```                                                                                                                   |                 | 
| 로그인       | /api/auth/sign-in  | POST   |                | ```{ "username": "Alice", "password": 30 }```                               | ```{ "result": "SUCCESS", "data": true }```                                                                                                                   |                 | 
| 게시글 생성    | /api/post          | POST   | auth           | ```{ "title": "title","body": "contents", "managePassword": "password" }``` | ```{ "result": "SUCCESS", "data": true }```                                                                                                                   |                 |
| 게시글 목록 조회 | /api/post          | GET    |                |                                                                             | ```{ "result": SUCCESS, "data":[{"title": "title1", body: "contents", createdAt: 123456789},{"title": "title2", body: "contents2", createdAt: 1234567891}]``` |                 |
| 게시글 상세 조회 | /api/post/{postId} | GET    |                |                                                                             | ```{ "result": SUCCESS, "data": {title": "title","body": "contents", "managePassword": "password"} }```                                                       |                 |
| 게시글 수정    | /api/post/{postId} | PUT    | auth           | ```{ "title": "title","body": "contents", "managePassword": "password" }``` | ```{ "result":SUCCESS, "data":{"title": "title","body": "contents", "managePassword": "password" } }```                                                       |                 |
| 게시글 삭제    | /api/post/{postId} | DELETE | auth           |                                                                             | ```{ "result": "SUCCESS", "data": true }```                                                                                                                   |                 |

- USE CASE
  ![USE_CASE](/usecase.png)
- ERD
  ![ERD](/ERD.png)
