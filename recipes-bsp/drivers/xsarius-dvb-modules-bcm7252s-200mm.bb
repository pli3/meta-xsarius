DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.14.28"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20190401"

# @description : model_size is 200mm and 300mm.
# ex) bcmlinuxdvb_7252S-200mm-3.14.28-20161130.tar.gz.
# only 2 case. ( 200mm and 300mm )

SRC_URI = "http://en3homeftp.net/release/images/oedrivers/bcmlinuxdvb_${CHIP}-${MODEL_SIZE}-${KV}-${SRCDATE}.tar.gz"

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
SRC_URI[md5sum] = "23275c9c854123365caf3334d38bbaed"
SRC_URI[sha256sum] = "71ff85e690d76bac3f10ae2bf63d01ad47037199841d603cd848b754fbf078c2"
