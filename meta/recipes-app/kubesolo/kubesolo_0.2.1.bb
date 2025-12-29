DESCRIPTION = "KubeSolo - Lightweight Kubernetes for edge devices"
HOMEPAGE = "https://www.kubesolo.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Inherit dpkg-raw for binary packages in Isar
inherit dpkg-raw

# Set proper architecture for binary package
DPKG_ARCH = "arm64"

# ARM64 release for IOT2050
#SRC_URI = "https://github.com/portainer/kubesolo/releases/download/v${PV}/kubesolo-v${PV}-linux-arm64.tar.gz;sha256sum=4026d3eb77c39cf1087f525d4ad222f964e53e9739f4486b126811c8c4684cf7"
SRC_URI = "file://kubesolo-linux-arm64.zip;sha256sum=49718ed574234b64c4f3f866a94755d30a715578067dde6a736ad3eeae2e8110 \
           file://kubesolo.service \
           file://kubesolo-prestart.sh \
           file://postinst \
"

S = "${WORKDIR}/git"

prefix = "/usr"
bindir = "${prefix}/bin"
localbin = "${prefix}/local/bin"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/kubesolo ${D}${bindir}/kubesolo
    install -m 0755 ${WORKDIR}/kubesolo-prestart.sh ${D}${bindir}/kubesolo-prestart.sh
    
    # Install systemd service file
    install -v -d ${D}/usr/lib/systemd/system/
    install -v -m 644 ${WORKDIR}/kubesolo.service ${D}/usr/lib/systemd/system/
}

FILES:${PN} = "${bindir}/kubesolo \
               ${bindir}/kubesolo-prestart.sh \
               /usr/lib/systemd/system/kubesolo.service"

DEBIAN_DEPENDS = "iptables, libsqlite3-0"
RCONFLICTS:${PN} = "docker"