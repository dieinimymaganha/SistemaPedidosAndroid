# Generated by Django 2.2.6 on 2019-11-23 16:36

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('produtos', '0001_initial'),
        ('pedidos', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='pedido',
            name='id_produto',
            field=models.ManyToManyField(to='produtos.Produto'),
        ),
    ]