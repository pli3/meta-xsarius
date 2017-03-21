DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.14.28"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20170321"


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
SRC_URI[md5sum] = "3292821ef08eb6748aacfdc2e3b0e41a"
SRC_URI[sha256sum] = "78ece19fcb6bb6abd850eef984ae2aa2e8b1be5ffabfd68c82a45d8fb29b2a6b"
