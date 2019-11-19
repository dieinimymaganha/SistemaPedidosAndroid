'use strict'

const Model = use('Model')

class Pedido extends Model {
  user () {
    return this.belongsTo('App/Models/User')
  }

  cliente () {
    return this.belongsTo('App/Models/Cliente')
  }

  item_do_pedidos () {
    return this.hasMany('App/Models/ItemDoPedido')
  }
}

module.exports = Pedido
