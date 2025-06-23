#!/usr/bin/env python3
import json

inventory = {
  "webservers": {
    "hosts": ["127.0.0.1"],
    "vars": {"ansible_connection":"local"}
  },
  "dbservers": {
    "hosts": ["127.0.0.1"],
    "vars": {"ansible_connection":"local"}
  },
  "_meta": {"hostvars": {}}
}

print(json.dumps(inventory))
