DESCRIPTION = "KubeSolo - Lightweight Kubernetes for edge devices"
HOMEPAGE = "https://www.kubesolo.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Inherit dpkg-raw for binary packages in Isar
inherit dpkg-raw

# Set proper architecture for binary package
DPKG_ARCH = "arm64"

# ARM64 release for IOT2050
SRC_URI = "https://github.com/portainer/kubesolo/releases/download/v${PV}/kubesolo-v${PV}-linux-arm64.tar.gz;sha256sum=4026d3eb77c39cf1087f525d4ad222f964e53e9739f4486b126811c8c4684cf7"

S = "${WORKDIR}/git"

prefix = "/usr"
bindir = "${prefix}/bin"
localbin = "${prefix}/local/bin"

# Override dh_usrlocal to handle symlinks in /usr/local
DEBIAN_RULES_EXTRA = "override_dh_usrlocal:\n\t# Skip dh_usrlocal checks for symlinks\n"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/kubesolo ${D}${bindir}/kubesolo
    
    # Create symlink for runc compatibility
    install -d ${D}${localbin}
    ln -sf /usr/sbin/runc ${D}${localbin}/runc
}

FILES:${PN} = "${bindir}/kubesolo ${localbin}/runc"

DEBIAN_DEPENDS = "iptables, libsqlite3-0"
RCONFLICTS:${PN} = "docker"