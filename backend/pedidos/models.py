from django.db import models
from clientes.models import Cliente
from produtos.models import Produto


class Pedido(models.Model):
    data = models.DateField()
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE)


class ItensDosPedidos(models.Model):
    id_pedido = models.ForeignKey(Pedido, on_delete=models.CASCADE)
    id_produto = models.ManyToManyField(Produto)
    quantidade = models.IntegerField(max_length=11)