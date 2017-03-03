DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.14.28"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20170303"


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
SRC_URI[md5sum] = "b87e1951dda36695583ec6b808d16462"
SRC_URI[sha256sum] = "e83de5e8a9e0acebb64b652dae8d730ee718bc0ec35f12f41445c9c786635726"
