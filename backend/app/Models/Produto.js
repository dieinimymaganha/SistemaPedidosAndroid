'use strict'

const Model = use('Model')

class Produto extends Model {
  user () {
    return this.belongsTo('App/Models/User')
  }

  item_do_pedidos () {
    return this.hasOne('App/Models/ItemDoPedido')
  }
}

module.exports = Produto
