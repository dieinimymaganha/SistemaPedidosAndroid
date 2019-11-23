from django.db import models
from produtos.models import Produto
from pedidos.models import Pedido


class ItensDosPedidos(models.Model):
    id_pedido = models.ForeignKey(Pedido, on_delete=models.CASCADE)
    id_produto = models.ManyToManyField(Produto)
    quantidade = models.IntegerField(max_length=11)
