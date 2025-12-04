# API Estoque - Demonstração de Funcionalidades

Este README demonstra como usar os principais endpoints da API de controle de estoque, incluindo clientes, produtos, estoque e vendas. Os exemplos estão prontos para uso no Thunder Client ou qualquer outro cliente REST.

```http
1️⃣ Criar Categorias
# Criar categoria "Eletrônicos"
POST http://localhost:8080/api/categorias
Content-Type: application/json

{
    "nome": "Eletrônicos"
}

# Criar categoria "Alimentos"
POST http://localhost:8080/api/categorias
Content-Type: application/json

{
    "nome": "Alimentos"
}

2️⃣ Criar Fornecedores
# Criar fornecedor "Fornecedor A"
POST http://localhost:8080/api/fornecedores
Content-Type: application/json

{
    "nome": "Fornecedor A"
}

# Criar fornecedor "Fornecedor B"
POST http://localhost:8080/api/fornecedores
Content-Type: application/json

{
    "nome": "Fornecedor B"
}

3️⃣ Criar Produtos com Estoque
# Criar produto "Smartphone" com estoque
POST http://localhost:8080/api/produtos
Content-Type: application/json

{
    "nome": "Smartphone",  # Produto usado para demonstração de venda
    "categoria": {"id": 1},
    "estoque": {
        "quantidade": 10,  # Quantidade inicial em estoque
        "local": "Prateleira A1"
    }
}

# Criar produto "Notebook" com estoque
POST http://localhost:8080/api/produtos
Content-Type: application/json

{
    "nome": "Notebook",
    "categoria": {"id": 1},
    "estoque": {
        "quantidade": 5,
        "local": "Prateleira A2"
    }
}

# Criar produto "Chocolate" com estoque
POST http://localhost:8080/api/produtos
Content-Type: application/json

{
    "nome": "Chocolate",
    "categoria": {"id": 2},
    "estoque": {
        "quantidade": 50,
        "local": "Prateleira B1"
    }
}

4️⃣ Criar Cliente
# Criar cliente "Mateus Pinheiro"
POST http://localhost:8080/api/clientes
Content-Type: application/json

{
    "nome": "Mateus Pinheiro",
    "email": "mateus@email.com",
    "telefone": "11999999999"
}

5️⃣ Registrar uma Venda (Estoque suficiente)
# Registrar venda do produto "Smartphone" (estoque suficiente)
POST http://localhost:8080/api/vendas
Content-Type: application/json

{
    "clienteId": 1,
    "itens": [
        {
            "produtoId": 1,
            "quantidade": 2
        }
    ]
}
# Este exemplo vai baixar corretamente o estoque do produto "Smartphone".

6️⃣ Registrar uma Venda (Estoque insuficiente)
# Tentar registrar venda do produto "Notebook" com quantidade maior que o estoque
POST http://localhost:8080/api/vendas
Content-Type: application/json

{
    "clienteId": 1,
    "itens": [
        {
            "produtoId": 2,
            "quantidade": 100
        }
    ]
}
# Aqui o sistema deve retornar erro (Estoque insuficiente) e não alterar o estoque (rollback).

7️⃣ Consultas
# Consultar todos os produtos (ver estoque)
GET http://localhost:8080/api/produtos

# Consultar todos os clientes
GET http://localhost:8080/api/clientes

# Consultar todas as vendas
GET http://localhost:8080/api/vendas
