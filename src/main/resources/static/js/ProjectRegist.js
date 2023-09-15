
const handler = {
  init() {

    const fileInput = $('#inputGroupFile02');
    const preview = $('.fileThumbnailZone');
    $('#inputGroupFile02').change(() => {
      $('.fileThumbnailZone').children().remove();
      const files = Array.from($('#inputGroupFile02')[0].files);
      files.forEach(file => {
        var previewText = $(`<li><p id="${file.lastModified}">${file.name}<button data-index='${file.lastModified}' class='file-remove' style="margin-left:20px;">X</button></p></li>`);
        preview.append(previewText);
      });
    });
  },

  removeFile: () => {
    document.addEventListener('click', (e) => {
      if (e.target.className !== 'file-remove') return;
      const removeTargetId = e.target.dataset.index;
      const removeTarget = document.getElementById(removeTargetId);
      const files = document.querySelector('#inputGroupFile02').files;
      const dataTranster = new DataTransfer();


      Array.from(files)
        .filter(file => file.lastModified != removeTargetId)
        .forEach(file => {
          dataTranster.items.add(file);
        });

      document.querySelector('#inputGroupFile02').files = dataTranster.files;

      removeTarget.remove();
    })
  }
}

handler.init();
handler.removeFile();

$('.submitBtn').click((e) => {
  if ($('.pjt_nm').val() === "") {
    e.preventDefault();

    $('.pjt_nm').focus().attr('placeholder', "제목은 필수입니다.");
  }
})