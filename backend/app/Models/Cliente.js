'use strict'

const Model = use('Model')

class Cliente extends Model {
  user () {
    return this.belongsTo('App/Models/User')
  }

  pedidos () {
    return this.hasOne('App/Models/Pedido')
  }
}

module.exports = Cliente
