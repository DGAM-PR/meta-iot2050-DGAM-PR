DESCRIPTION = "KubeSolo - Lightweight Kubernetes for edge devices"
HOMEPAGE = "https://www.kubesolo.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Inherit dpkg-raw for binary packages in Isar
inherit dpkg-raw

# Set proper architecture for binary package
DPKG_ARCH = "arm64"

# ARM64 release for IOT2050
SRC_URI = "https://github.com/portainer/kubesolo/releases/download/v${PV}/kubesolo-v${PV}-linux-arm64.tar.gz"

SRC_URI[sha256sum] = "020b9951d1285df5ad484786098a98e95a68e209456c1be4aeaff81681070f66"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/kubesolo ${D}${bindir}/kubesolo
}

FILES:${PN} = "${bindir}/kubesolo"

DEBIAN_DEPENDS = "iptables, libsqlite3-0"
RCONFLICTS:${PN} = "docker"