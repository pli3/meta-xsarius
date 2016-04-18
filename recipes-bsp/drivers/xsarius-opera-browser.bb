DESCRIPTION = "vuplus opera browser util"
LICENSE = "CLOSED"

SRCREV = "${AUTOREV}"
PR = "r1"

SRC_URI = "git://github.com/pli3/xsarius-opera-browser.git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
"

ALLOW_EMPTY_${PN} = "1"

S = "${WORKDIR}/git"
DEPLOY_DIR = "${TMPDIR}/deploy"

XSARIUS_OPERA_BROWSER = " \
	xsarius-opera-browser_1.0-r1_mips32el.ipk \
"

do_install() {
}
python populate_packages_prepend () {
    p = ""
    plugins = bb.data.getVar('XSARIUS_OPERA_BROWSER', d, 1)

    if plugins is not None:
        for package in plugins.split():
            p += package.split('_')[0] + " "

    bb.data.setVar('PACKAGES', p, d)
}

do_deploy() {
    install -d 0755 ${WORKDIR}/deploy-ipk/mips32el

    for i in ${VUPLUS_OPERA_BROWSER}; do
        if [ -f $i ]; then
            install -m 0644 $i ${WORKDIR}/deploy-ipk/mips32el;
            install -m 0644 $i ${DEPLOY_DIR}/ipk/mips32el;
        fi
    done;

    pkgdir=${DEPLOY_DIR_IPK}/mips32el
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
}

addtask do_deploy before do_package_write after do_package_write_ipk

