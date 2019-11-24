from rest_framework.serializers import ModelSerializer
from pedidos.models import Pedido


class PedidoSerializer(ModelSerializer):
    class Meta:
        model = Pedido
        fields = '__all__'
