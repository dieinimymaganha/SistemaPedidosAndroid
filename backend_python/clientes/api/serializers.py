from rest_framework.serializers import ModelSerializer
from clientes.models import Cliente


class AtracaoSerializer(ModelSerializer):
    class Meta:
        model = Cliente
        fields = '__all__'
