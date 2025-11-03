DESCRIPTION = "KubeSolo - Lightweight Kubernetes for edge devices"
HOMEPAGE = "https://www.kubesolo.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# ARM64 release for IOT2050
SRC_URI = "https://github.com/portainer/kubesolo/releases/download/v${PV}/kubesolo-v${PV}-linux-arm64.tar.gz"

# To get checksum: wget <url> && sha256sum kubesolo-v0.2.0-linux-arm64.tar.gz
SRC_URI[sha256sum] = "REPLACE_WITH_ACTUAL_SHA256"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/kubesolo ${D}${bindir}/kubesolo
}

FILES:${PN} = "${bindir}/kubesolo"

# Runtime dependencies required by KubeSolo
RDEPENDS:${PN} = "iptables libsqlite3-dev"

# Prevent Docker from being installed alongside KubeSolo
RCONFLICTS:${PN} = "docker"