DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.14.28"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20180511"

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
SRC_URI[md5sum] = "e5beb1aee81be537ff540a1e301f6739"
SRC_URI[sha256sum] = "a7b8a617aee4ea82eca4daaa3a9903129a82b8661ffdc458ce3baa606c10b7fa"
