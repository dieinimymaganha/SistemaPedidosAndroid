from django.db import models


class Produto(models.Model):
    descricao = models.CharField(max_length=45)

    def __str__(self):
        return self.descricao
