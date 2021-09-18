module.exports = {
    css: {
        loaderOptions: {
            sass: {
                prependData: `
                    @import "@/scss/_reset.scss";
                    @import "@/scss/_variables.scss";
                    @import "@/scss/_functions.scss";
                    @import "@/scss/_fonts.scss";
                    @import "@/scss/_global.scss";
                    @import "@/scss/_icons.scss";
                    @import "@/scss/_box.scss";
                `
            }
        }
    }
};