'use strict'

const Pedido = use('App/Models/Pedido')
const Cliente = use('App/Models/Cliente')

class PedidoController {

  async index ({ request, response, view }) {
  }

  async store ({ auth, request, response }) {
    const { id } = auth.user
    const data = request.only([
      'cpf'
    ])

    const cliente = await Cliente
      .query()
      .where('cpf', '=', data.cpf)
      .with('pedidos')
      .first()

    if (cliente === null) {
      return null
    } else {
      const pedido = await cliente.pedidos().first()

      if (pedido === null) {
        return await Pedido.create({
          cliente_id: cliente.id,
          data: new Date(),
          user_id: id
        })
      } else {
        return pedido
      }
    }
  }

  async show ({ params }) {
    const pedido = await Pedido.findOrFail(params.id)

    return pedido
  }

  async update ({ params, request, response }) {
  }

  async destroy ({ params, request, response }) {
  }
}

module.exports = PedidoController
