from django.db import models
from produtos.models import Produto
from pedidos.models import Pedido


class ItensDosPedidos(models.Model):
    id_pedido = models.ForeignKey(Pedido, on_delete=models.CASCADE)
    id_produto = models.ForeignKey(Produto, on_delete=models.CASCADE)
    quantidade = models.IntegerField()
