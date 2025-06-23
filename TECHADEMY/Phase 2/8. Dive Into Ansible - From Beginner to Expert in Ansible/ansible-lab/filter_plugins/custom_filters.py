def reverse_string(value):
    return value[::-1]

class FilterModule(object):
    def filters(self):
        return {
            'reverse': reverse_string
        }
