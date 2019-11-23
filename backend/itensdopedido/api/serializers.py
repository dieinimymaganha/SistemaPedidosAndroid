from rest_framework.serializers import ModelSerializer
from itensdopedido.models import ItensDosPedidos


class ItensDosPedidosSerializer(ModelSerializer):
    class Meta:
        model = ItensDosPedidos
        fields = '__all__'
