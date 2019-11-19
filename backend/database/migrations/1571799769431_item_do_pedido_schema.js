'use strict'

const Schema = use('Schema')

class ItemDoPedidoSchema extends Schema {
  up () {
    this.create('item_do_pedidos', (table) => {
      table.increments()
      table
        .integer('user_id')
        .unsigned()
        .references('id')
        .inTable('users')
        .onUpdate('CASCADE')
        .onDelete('CASCADE')
      table
        .integer('pedido_id')
        .unsigned()
        .references('id')
        .inTable('pedidos')
        .onUpdate('CASCADE')
        .onDelete('CASCADE')
      table
        .integer('produto_id')
        .unsigned()
        .references('id')
        .inTable('produtos')
        .onUpdate('CASCADE')
        .onDelete('CASCADE')
      table
        .integer('quantidade')
        .notNullable()
      table.timestamps()
    })
  }

  down () {
    this.drop('item_do_pedidos')
  }
}

module.exports = ItemDoPedidoSchema
