from rest_framework.serializers import ModelSerializer
from produtos.models import Produto


class ProdutoSerializer(ModelSerializer):
    class Meta:
        model = Produto
        fields = '__all__'
