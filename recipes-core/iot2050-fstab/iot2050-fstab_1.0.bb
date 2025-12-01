inherit dpkg-raw

DESCRIPTION = "Custom fstab configuration for IOT2050"

SRC_URI = "file://fstab"

do_install() {
    install -v -d ${D}/etc
    install -v -m 644 ${WORKDIR}/fstab ${D}/etc/fstab
}