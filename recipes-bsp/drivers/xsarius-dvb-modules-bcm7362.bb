DESCRIPTION = "Hardware drivers for BCM7362 chip"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20151006"

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

SRC_URI[md5sum] = "dd5a372de9cc7c243be0d9414cad01cf"
SRC_URI[sha256sum] = "51fb8311521959e4ab9f30112f585b416e5958df8b273e3a3b24bae069cebbe2"
