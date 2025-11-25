DESCRIPTION = "kubectl - Kubernetes command-line tool"
HOMEPAGE = "https://kubernetes.io/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Inherit dpkg-raw for binary packages in Isar
inherit dpkg-raw

# Set proper architecture for binary package
DPKG_ARCH = "arm64"

# ARM64 release KubeCTL
## More info: https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/
SRC_URI = "https://dl.k8s.io/release/v${PV}/bin/linux/arm64/kubectl;sha256sum=00b182d103a8a73da7a4d11e7526d0543dcf352f06cc63a1fde25ce9243f49a0"

S = "${WORKDIR}"

prefix = "/usr"
bindir = "${prefix}/bin"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/kubectl ${D}${bindir}/kubectl
}

FILES:${PN} = "${bindir}/kubectl"
