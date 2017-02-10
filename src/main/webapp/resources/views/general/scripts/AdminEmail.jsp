<script>
	function rowStyle(value, row, index) {

		if (!value.sendDate) {
			return {
				classes : 'text-nowrap another-class',
				css : {
					"background" : "rgba(255,0,0,0.2)"
				}
			};
		} else {
			return {
				classes : 'text-nowrap another-class'
			}
		}
	}
</script>