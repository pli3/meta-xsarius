DESCRIPTION = "Hardware drivers for BCM7362 chip"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20150918"

SRC_URI = "http://en3homeftp.net/release/images/oedrivers/bcmlinuxdvb_7362-${KV}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1" 

inherit module

do_compile() {
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf"

do_install() {
        install -d ${D}/lib/modules/${KV}/extra
        for f in ${S}/lib/modules/${KV}/extra/*.ko; do
            install -m 0644 $f ${D}/lib/modules/${KV}/extra;
        done
        install -d ${D}/${sysconfdir}/modules-load.d
        for i in `ls ${D}/lib/modules/${KV}/extra | grep \\.ko | sed -e 's/.ko//g'`; do
            echo $i _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
        done
}

SRC_URI[md5sum] = "0db0d1d970d97e1800ee9cf35052604e"
SRC_URI[sha256sum] = "9f48575da41444e13111d0745bb256d91986039c5dea839d18610c9c79d5d418"
