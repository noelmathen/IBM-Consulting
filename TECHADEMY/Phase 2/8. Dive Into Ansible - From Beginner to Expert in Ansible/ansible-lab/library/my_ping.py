#!/usr/bin/env python3
from ansible.module_utils.basic import AnsibleModule

def main():
    module = AnsibleModule(argument_spec={})
    rc, out, err = module.run_command(['ping','-c','1','8.8.8.8'])
    if rc == 0:
        module.exit_json(changed=False, msg=out.strip())
    else:
        module.fail_json(msg=err.strip())

if __name__ == '__main__':
    main()
