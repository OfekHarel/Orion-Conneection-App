import socket

from App.app.networking.util.Constants import Network


class OrionConnectionApp:
    def __init__(self):
        self.comm_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        self.broadcast_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.broadcast_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.broadcast_socket.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
        self.broadcast_socket.bind(('', Network.BROAD_PORT))

    def connect_comm(self, param=tuple):
        try:
            self.comm_socket.connect(param)

        except ConnectionRefusedError as err:
            pass


