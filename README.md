# API Estoque - Demonstração de Funcionalidades

Este README demonstra como usar os principais endpoints da API de controle de estoque, incluindo clientes, produtos, estoque e vendas. Os exemplos estão prontos para uso no **Thunder Client** ou qualquer outro cliente REST.

---

## 1️⃣ Criar Categorias

```http
POST http://localhost:8080/api/categorias
Content-Type: application/json

{
    "nome": "Eletrônicos"
}

POST http://localhost:8080/api/categorias
Content-Type: application/json

{
    "nome": "Alimentos"
}

## 2️⃣ Criar Fornecedores
POST http://localhost:8080/api/fornecedores
Content-Type: application/json

{
    "nome": "Fornecedor A"
}

POST http://localhost:8080/api/fornecedores
Content-Type: application/json

{
    "nome": "Fornecedor B"
}

