from rest_framework.viewsets import ModelViewSet
from itensdopedido.models import ItensDosPedidos
from .serializers import ItensDosPedidosSerializer


class ItemDoPedidoViewSet(ModelViewSet):
    queryset = ItensDosPedidos.objects.all()
    serializer_class = ItensDosPedidosSerializer
