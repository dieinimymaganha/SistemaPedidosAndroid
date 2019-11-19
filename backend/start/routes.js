'use strict'

const Route = use('Route')

Route.post('/users', 'UserController.create')
Route.post('/sessions', 'SessionController.create')

Route.resource('clientes', 'ClienteController')
  .apiOnly()


Route.resource('produtos', 'ProdutoController')
  .apiOnly()


Route.resource('pedidos', 'PedidoController')
  .apiOnly()


  Route.resource('item-do-pedido', 'ItemDoPedidoController')
  .apiOnly()

