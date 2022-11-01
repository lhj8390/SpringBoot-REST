const HtmlWebpackPlugin = require('html-webpack-plugin');

const port = process.env.PORT || 2000;

module.exports = {
	// 개발환경
	mode: 'development',

	// 애플리케이션 시작 경로
	entry: './src/index.js',

	// 번들된 파일 경로
	output: {
		filename: 'bundle.[hash].js',
	},

	module: {
		rules: [
			{
				test: /\.(js|jsx)$/,
				exclude: /node_modules/,
				use: {
					loader: 'babel-loader',
				},
			},

			{
				test: /\.html$/,
				use: [
					{
						loader: 'html-loader',
						options: {
							minimize: true,
						},
					},
				],
			},

			// style-loader, css-loader 구성
			{
				test: /\.css$/i,
				exclude: /\.module\.css$/i, // 모듈 파일 제외 설정
				use: ['style-loader', 'css-loader'],
			},

			{
				test: /\.module\.css$/i,
				use: [
					'style-loader',
					{
						loader: 'css-loader',
						options: {
							modules: true,
						},
					},
				],
			},

			{
				test: /\.(png|svg|jpg|jpeg|gif|ico)$/,
				exclude: /node_modules/,
				use: ['file-loader?name=[name].[ext]'],
			},
		],
	},

	plugins: [
		new HtmlWebpackPlugin({
			template: 'public/index.html',
		}),
	],

	// 개발 서버 설정
	devServer: {
		host: 'localhost',
		port: port,
		open: true, // open page when start
		historyApiFallback: true,
        proxy: {
            "/api": {
                "target": "http://localhost:8080"
            }
        }
	},
};