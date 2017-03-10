SECTION = "base"
LICENSE = "CLOSED"

PR="${SRCDATE}.${SRCDATE_PR}"

SRC_URI = "http://en3homeftp.net/pub/src/libgles-arm-${PV}-${PR}.tar.gz"

S = "${WORKDIR}/libgles"

do_install() {
             install -d ${D}${libdir}
             install -m 0755 ${S}/usr/lib/*.so ${D}${libdir}
             ln -s libv3ddriver.so ${D}${libdir}/libEGL.so
             ln -s libv3ddriver.so ${D}${libdir}/libGLESv2.so

             install -d ${D}${includedir}
             cp -a ${S}/include/* ${D}${includedir}/
}

do_package_qa() {
}

do_package_qa[noexec] = "1"

PACKAGE_ARCH := "${MACHINE}"
PACKAGES = "${PN}"
FILES_${PN}="/usr/lib /usr/include"

INSANE_SKIP_${PN} = "already-stripped"

