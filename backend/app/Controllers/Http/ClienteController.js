'use strict'

const Cliente = use('App/Models/Cliente')

class ClienteController {

  async index () {
    const clientes = Cliente.query()
      .with('pedidos')
      .fetch()

    return clientes
  }

  async store ({request }) {
    // const { id } = auth.user
    const data = request.only([
      'cpf',
      'nome',
      'sobreNome'
    ])

    const cliente = await Cliente.create({ ...data, user_id: id })

    return cliente
  }

  async show ({ params }) {
    const cliente = await Cliente
      .query()
      .where('cpf', '=', params.id)
      .with('pedidos')
      .first()

    return cliente
  }

  async update ({ params, request }) {
    const cliente = await Cliente.findOrFail(params.id)

    const data = request.only([
      'cpf',
      'nome',
      'sobreNome'
    ])

    cliente.merge(data)

    await cliente.save()

    return cliente
  }

  async destroy ({ params }) {
    const cliente = await Cliente.findOrFail(params.id)

    //TODO: Ver como tratar este ponto
    //await cliente.load('pedidos')

    await cliente.delete()
  }
}

module.exports = ClienteController
