from django.db import models
from clientes.models import Cliente
from produtos.models import Produto


class Pedido(models.Model):
    data = models.DateField()
    id_cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE)