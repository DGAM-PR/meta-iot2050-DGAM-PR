inherit dpkg-raw

DESCRIPTION = "Override /var mount options to enable exec"

do_install() {
    # Create drop-in directory for var.mount unit
    install -v -d ${D}/etc/systemd/system/var.mount.d
    
    # Create override configuration
    cat > ${D}/etc/systemd/system/var.mount.d/override.conf <<EOF
[Mount]
Options=rw,nosuid,nodev,exec,relatime
EOF
}