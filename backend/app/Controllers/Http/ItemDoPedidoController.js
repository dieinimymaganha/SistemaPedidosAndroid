'use strict'

const ItemDoPedido = use('App/Models/ItemDoPedido')
const Env = use('Database')

class ItemDoPedidoController {

  async index() {
      const pedidos = await Env
        .select(
          'clientes.nome',
          'clientes.cpf',
          'produtos.descricao',
          'item_do_pedidos.quantidade'
        )
        .from('clientes')
        .innerJoin(
          'pedidos',
          'clientes.id',
          'pedidos.cliente_id'
        )
        .innerJoin(
          'item_do_pedidos',
          'item_do_pedidos.pedido_id',
          'pedidos.id'
        )
        .innerJoin(
          'produtos',
          'item_do_pedidos.produto_id',
          'produtos.id'
        )

      return pedidos
  }

  async store({ auth, request }) {
    const { id } = auth.user
    const data = request.only([
      'pedido_id',
      'produto_id',
      'quantidade'
    ])

    const itemDoPedido = await ItemDoPedido.create({ ...data, user_id: id })

    return itemDoPedido
  }

  async show({ params }) {
    const pedidos = await Env
      .select(
        'clientes.nome',
        'clientes.cpf',
        'produtos.descricao',
        'item_do_pedidos.quantidade'
      )
      .from('clientes')
      .innerJoin(
        'pedidos',
        'clientes.id',
        'pedidos.cliente_id'
      )
      .innerJoin(
        'item_do_pedidos',
        'item_do_pedidos.pedido_id',
        'pedidos.id'
      )
      .innerJoin(
        'produtos',
        'item_do_pedidos.produto_id',
        'produtos.id'
      )
      .where('clientes.cpf', '=', params.id)

    return pedidos
  }

  async update({ params, request, response }) {
  }

  async destroy({ params, request, response }) {
  }
}

module.exports = ItemDoPedidoController
