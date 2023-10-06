/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
       colors: {'elrond':'#24f7dd',
                'elrond-700':'#01665A',
                'elrond-800':'#014B42',
                'elrond-900':'#03473F',
            },
    },
  },
  plugins: [],
}