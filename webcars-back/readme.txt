Para gerar um token fa�a uma requisi��o POST para http://localhost:8080/webcars-back/login com o seguinte JSON no body:
{

	"username":"admin",

	"password":"admin"

}
O token vir� no header de nome "Token" da resposta.

Para acessar um servi�o protegido (Ex: http://localhost:8080/webcars-back/autorizacao/getAll) � preciso passar no header de nome "Autorization" o texto "Bearer <token>", onde <token> deve ser substitu�do pelo token gerado. 