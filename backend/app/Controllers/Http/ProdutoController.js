'use strict'

const Produto = use('App/Models/Produto')

class ProdutoController {

  async index () {
    const produtos = Produto.all()

    return produtos
  }

  async store ({ auth, request }) {
    const { id } = auth.user
    const data = request.only([
      'descricao'
    ])

    const produto = await Produto.create({ ...data, user_id: id })

    return produto
  }

  async show ({ params }) {
    const produto = await Produto.findOrFail(params.id)

    return produto
  }

  async update ({ params, request }) {
    const produto = await Produto.findOrFail(params.id)

    const data = request.only([
      'descricao'
    ])

    produto.merge(data)

    await produto.save()

    return produto
  }

  async destroy ({ params, auth, response }) {
    const produto = await Produto.findOrFail(params.id)

    if (produto.user_id !== auth.user.id) {
      return response.status(401).send({ error: 'Not authorized' })
    }

    await produto.delete()
  }
}

module.exports = ProdutoController
