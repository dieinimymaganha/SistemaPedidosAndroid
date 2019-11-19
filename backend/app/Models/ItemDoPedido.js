'use strict'

const Model = use('Model')

class ItemDoPedido extends Model {
  user () {
    return this.belongsTo('App/Models/User')
  }

  pedidos () {
    return this.belongsTo('App/Models/Pedido')
  }

  produtos () {
    return this.belongsTo('App/Models/Produto')
  }
}

module.exports = ItemDoPedido
